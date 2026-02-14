import { twMerge } from "tailwind-merge";
import { cva } from "class-variance-authority";

const buttonStyles = cva("btn rounded-xl", {
	variants: {
		variant: {
			primary: "btn-primary",
			secondary: "btn-secondary",
			warning: "btn-warning",
		},
		size: {
			sm: "btn-sm max-w-xs",
			md: "btn-md max-w-md",
			lg: "btn-lg max-w-lg",
		},
	},
	defaultVariants: {
		size: "md",
		variant: "primary",
	},
});

type ButtonProps = React.ComponentProps<"button"> &
	Partial<{
		variant?: "primary" | "secondary" | "warning";
		size?: "sm" | "md" | "lg";
		loading?: boolean;
	}>;

export function Button(props: Readonly<ButtonProps>) {
	const disabled = props?.disabled || props?.loading;
	const onClickHandler = props?.loading ? undefined : props.onClick;
	return (
		<button
			type="button"
			disabled={disabled}
			onClick={onClickHandler}
			className={twMerge(
				buttonStyles({ variant: props.variant, size: props.size }),
			)}
		>
			<span className={props.loading ? "loading loading-spinner" : ""}>
				{props.children}
			</span>
		</button>
	);
}
